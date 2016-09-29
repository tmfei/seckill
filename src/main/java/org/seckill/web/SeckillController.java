/**
 * Project Name:seckill
 * File Name:SeckillController.java
 * Package Name:org.seckill.web
 * Date:2016年9月12日下午2:50:57
 * Copyright (c) 2016, Qingdao-Partner All Rights Reserved.
 *
*/

package org.seckill.web;

import java.util.Date;
import java.util.List;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.dto.SeckillResult;
import org.seckill.entity.SecKill;
import org.seckill.enums.SeckillStatEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName:SeckillController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年9月12日 下午2:50:57 <br/>
 * @author   wenfei
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */

@Controller //@Service @Component
@RequestMapping("/seckill") //url:/模块/资源/{id}/细分   /seckill/list
public class SeckillController
{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private SeckillService seckillService;
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model)
    {
        //获取列表页
        List<SecKill> seckillList = seckillService.getSeckillList();
        model.addAttribute("list", seckillList);
        
        //list.jsp + model = ModelAndView
        return "list"; //WEB-INF/jsp/"list".jsp
    }
    
    @RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId, Model model)
    {
        if (null == seckillId)
        {
            return "redirect:/seckill/list";
        }
        
        SecKill secKill = seckillService.getById(seckillId);
        
        if (null == secKill)
        {
            return "forward:/seckill/list";
        }
        
        model.addAttribute("seckill", secKill);
        
        return "detail";
    }
    
    //ajax接口，返回 json
    @RequestMapping(value = "/{seckillId}/exposer", method = RequestMethod.POST,
        produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<Exposer> exposer(@PathVariable Long seckillId)
    {
        SeckillResult<Exposer> result;
        
        try
        {
            Exposer exposer = seckillService.exposeSeckillUrl(seckillId);
            result = new SeckillResult<Exposer>(true, exposer);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            result = new SeckillResult<Exposer>(false, e.getMessage());
        }
        
        return result;
    }
    
    @RequestMapping(value = "/{seckillId}/{md5}/execution", method = RequestMethod.POST,
        produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId, 
        @PathVariable("md5") String md5, @CookieValue(value="killPhone", required = false) Long phone)
    {
        if (null == phone)
        {
            return new SeckillResult<SeckillExecution>(false, "未注册");
        }
        
        try
        {
//            SeckillExecution excution = seckillService.excuteSeckill(seckillId, phone, md5);
            //使用存储过程
            SeckillExecution excution = seckillService.excuteSeckillProcedure(seckillId, phone, md5);
            return new SeckillResult<SeckillExecution>(true, excution);
        }
        catch(SeckillCloseException e)
        {
            SeckillExecution excution = new SeckillExecution(seckillId, SeckillStatEnum.END);
            return new SeckillResult<SeckillExecution>(true, excution);
        }
        catch (RepeatKillException e) {
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStatEnum.REPEAT_KILL);
            return new SeckillResult<SeckillExecution>(true, seckillExecution);
        }
        catch (Exception e) 
        {
            logger.error(e.getMessage(), e);
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStatEnum.INNER_ERROR);
            return new SeckillResult<SeckillExecution>(true, seckillExecution);
        }
        
            
    }
    
    @RequestMapping(value = "/time/now", method = RequestMethod.GET)
    @ResponseBody
    public SeckillResult<Long> time()   
    {
        Date now = new Date();
        
        return new SeckillResult<Long>(true, now.getTime());
    }
}

