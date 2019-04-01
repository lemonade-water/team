package com.sky.team.business.service.imp;

import com.sky.team.business.dao.PersonVideoDao;
import com.sky.team.business.pojo.ResultMessage;
import com.sky.team.business.service.PersonVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonVideoServiceImp implements PersonVideoService {

    @Autowired
    private PersonVideoDao personVideoDao;

    @Override
    public ResultMessage correction(String personvideoId, String type) {
        /*判断是不通过还是通过*/

        boolean corrction = personVideoDao.corrction(personvideoId, type);
        if(corrction){
            return ResultMessage.setResultMessage("200","成功");
        }else {
            return ResultMessage.setResultMessage("500","失败");
        }
    }
}
