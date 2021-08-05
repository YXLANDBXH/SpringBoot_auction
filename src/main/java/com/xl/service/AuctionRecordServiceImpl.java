package com.xl.service;

import com.xl.domain.Auction;
import com.xl.domain.AuctionCustomerException;
import com.xl.mapper.AuctionAndUserAndRecord;
import com.xl.mapper.AuctionRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author XLong
 * @create 2021-08-05 16:05
 */

@Service
public class AuctionRecordServiceImpl implements AuctionRecordService {

    @Autowired
    private AuctionRecordMapper auctionRecordMapper;
    //自定义mapper
    @Autowired
    private AuctionAndUserAndRecord auctionAndUserAndRecord;

    /**
     1.有竞拍记录
     a.你出的价格不能低于所有价格的中的最高价
     给出错误提示: 不能低于所有价格的最高价  -> 异常
     b.你竞拍的时间不能晚于 当前活动的结束时间
     给出错误提示: 不能晚于活动的结束时间  -> 异常
     2.没有竞拍记录
     如果你出的价格低于 起拍价
     给出错误提示:  当前价格不能低于起拍价
     * @param auctionRecord
     */
    @Override
    public void saveAuctionRecord(com.xl.domain.AuctionRecord auctionRecord) throws Exception {
        //Integer转为String
        Auction auction = this.auctionAndUserAndRecord.selectAucAndRecordByAuctionId(String.valueOf(auctionRecord.getAuctionid()));
        if (new Date().after(auction.getAuctionendtime())) {
            throw new AuctionCustomerException("你竞拍的时间不能晚于当前活动的结束时间");
        }
        if (auction.getAuctionRecordList() != null && auction.getAuctionRecordList().size() > 0) { //一定存在竞拍记录
            //获取所有价格的最高价
            com.xl.domain.AuctionRecord maxRecordPrice = auction.getAuctionRecordList().get(0);

            if (auctionRecord.getAuctionprice().compareTo(maxRecordPrice.getAuctionprice()) < 1) {
                throw new AuctionCustomerException("竞拍价不能低于所有价格的最高价");
            }
        }else { //不存在竞拍记录
            if (auctionRecord.getAuctionprice().compareTo(auction.getAuctionstartprice()) < 1) {
                throw new AuctionCustomerException("当前价格不能低于起拍价");
            }
        }
        this.auctionRecordMapper.insert(auctionRecord);
    }
}
