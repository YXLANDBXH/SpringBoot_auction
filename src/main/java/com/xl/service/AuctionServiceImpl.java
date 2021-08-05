package com.xl.service;

import com.xl.domain.Auction;
import com.xl.domain.AuctionExample;
import com.xl.domain.AuctionRecord;
import com.xl.mapper.AuctionAndUserAndRecord;
import com.xl.mapper.AuctionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author XLong
 * @create 2021-08-05 9:57
 */
@Service
public class AuctionServiceImpl implements AuctionService {

    @Autowired
    private AuctionMapper auctionMapper;

    //自定义mapper
    @Autowired
    private AuctionAndUserAndRecord auctionAndUserAndRecord;

    /**
     * 条件查询
     * @param auction
     * @return
     */
    @Override
    public List<Auction> findAuction(Auction auction) {
        AuctionExample auctionExample = new AuctionExample();
        AuctionExample.Criteria criteria = auctionExample.createCriteria();
        // 完成多条件查询
        // 先判断当前用户是否有输入条件
        if (auction!=null) {
            if (auction.getAuctionname()!=null&&!"".equals(auction.getAuctionname())) {
                // 根据条件查询  模糊查询 拍卖品名称
                criteria.andAuctionnameLike("%"+auction.getAuctionname()+"%");
            }
            if (auction.getAuctiondesc()!=null&&!"".equals(auction.getAuctiondesc())) {
                // 根据 拍卖品描述进行 相等查询
                criteria.andAuctiondescEqualTo(auction.getAuctiondesc());

            }
            if (auction.getAuctionstarttime()!=null) {
                // 大于或等于 开始时间
                // 大于开始时间
                criteria.andAuctionstarttimeGreaterThan(auction.getAuctionstarttime());
            }
            if (auction.getAuctionendtime()!=null) {
                // 小于或等于结束时间
                // 小于结束时间
                criteria.andAuctionendtimeLessThan(auction.getAuctionendtime());
            }
            if (auction.getAuctionstartprice()!=null) {
                // 大于起拍价
                criteria.andAuctionstartpriceGreaterThan(auction.getAuctionstartprice());
            }
        }
        // 降序排序  根据开始时间 降序排序
        auctionExample.setOrderByClause("auctionstarttime desc");
        return this.auctionMapper.selectByExample(auctionExample);
    }

    /**
     * 根据auctionId查询出竞拍详情
     * @param auctionId
     * @return
     */
    @Override
    public Auction selectAucAndRecordByAuctionId(String auctionId) {
        return this.auctionAndUserAndRecord.selectAucAndRecordByAuctionId(auctionId);
    }

    @Override
    public void publishAuctions(Auction auction) {
        this.auctionMapper.insert(auction);
    }

    @Override
    public void updateAuctionById(Auction auction) {
        this.auctionMapper.updateByPrimaryKeySelective(auction);
    }

    @Override
    public void delAuctionById(String auctionId) {
        this.auctionMapper.deleteByPrimaryKey(Integer.parseInt(auctionId));
    }

    @Override
    public Auction findAuctionById(String auctionId) {
        return this.auctionMapper.selectByPrimaryKey(Integer.parseInt(auctionId));
    }

}
