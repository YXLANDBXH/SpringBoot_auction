package com.xl.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.xml.internal.ws.resources.HttpserverMessages;
import com.xl.domain.Auction;
import com.xl.domain.AuctionRecord;
import com.xl.domain.User;
import com.xl.service.AuctionRecordService;
import com.xl.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @author XLong
 * @create 2021-08-05 9:49
 */
@Controller
public class AuctionController {

    private static final int pageSize = 8;

    @Autowired
    private AuctionService auctionService;

    @Autowired
    private AuctionRecordService auctionRecordService;

    /**
     * 查询列表
     * @param auction
     * @param model
     * @return
     */
    @RequestMapping(value = "/queryAllAuctions")
    public String findAuction(@ModelAttribute("condition") Auction auction,
                              BindingResult bindingResult,
                              Model model,
                              @RequestParam(name = "pageNum",required = false,defaultValue = "1") int pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<Auction> auctionList = this.auctionService.findAuction(auction);
        //分页信息
        PageInfo pageInfo = new PageInfo(auctionList);
        //将分页数据存在model域中
        model.addAttribute("page",pageInfo);
        model.addAttribute("auctionList",auctionList);
        return "index";
    }

    /**
     * 根据auctionId查询出竞拍详情
     * @param auctionId
     * @param model
     * @return
     */
    @RequestMapping("/findAutionAndAucitionRecordListByAuctionId")
    public String findAutionAndAucitionRecordListByAuctionId(String auctionId,
                                                             Model model) {
        Auction auction = this.auctionService.selectAucAndRecordByAuctionId(auctionId);
        //将数据存在域中
        model.addAttribute("auctionDetail",auction);
        //转发到jsp视图
        return "auctionDetail";
    }

    /**
     * 竞拍功能
     * @param auctionRecord
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/saveAuctionRecord")
    public String saveAuctionRecord(AuctionRecord auctionRecord,
                                    HttpSession session) throws Exception {
        //封装userId
        User user = (User) session.getAttribute("user");
        auctionRecord.setUserid(user.getUserid());
        //封装auctionTime
        auctionRecord.setAuctiontime(new Date());
        this.auctionRecordService.saveAuctionRecord(auctionRecord);
        return "redirect:/findAutionAndAucitionRecordListByAuctionId?=auctionId=" + auctionRecord.getAuctionid();
    }
}














