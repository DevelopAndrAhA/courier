package kg.bish.courier.controllers;

import kg.bish.courier.models_to_db.ADV_COURIER_TB;
import kg.bish.courier.models_to_db.CourierDB;
import kg.bish.courier.models_to_serialize.UniverseJSON;
import kg.bish.courier.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ALTYNBEK on 05.02.2020.
 */
@Controller
@RequestMapping("adv_chek")
public class AdverstCheckController {
    @Autowired
    MyService myService;


    @ResponseBody
    @RequestMapping(value = "check",method = RequestMethod.POST)
    public Object check(@RequestBody UniverseJSON universeJSON) {
        if(universeJSON.getStatusStr().equals("COURIER")){
            CourierDB courierDB = new CourierDB();
            courierDB.setId(universeJSON.getCourierClientId());
            courierDB.setSecretKey(universeJSON.getSecretKey());
            if(myService.verifySecretKey(courierDB)){
                ADV_COURIER_TB result =  myService.checkAdverstStatus(universeJSON.getAdvId());
                if(result!=null){
                    return "{'adverst':false}";
                }else{
                    ADV_COURIER_TB adv_courier_tb = new ADV_COURIER_TB();
                        adv_courier_tb.setAdv_id(universeJSON.getAdvId());
                        adv_courier_tb.setClient_ID(universeJSON.getClientId());
                        adv_courier_tb.setCourier_ID(universeJSON.getCourierClientId());
                    boolean saving =  myService.save(adv_courier_tb);
                    if(saving){
                        return "{'adverst':success}";
                    }
                    return "{'adverst':false}";
                }
            }
        }
        return "{'adverst':false}";
    }


    @ResponseBody
    @RequestMapping(value = "un-check",method = RequestMethod.POST)
    public Object unCheck(@RequestBody UniverseJSON universeJSON) {
        if(universeJSON.getStatusStr().equals("COURIER")){
            CourierDB courierDB = new CourierDB();
            courierDB.setId(universeJSON.getCourierClientId());
            courierDB.setSecretKey(universeJSON.getSecretKey());
            if(myService.verifySecretKey(courierDB)){
                ADV_COURIER_TB result =  myService.checkAdverstStatus(universeJSON.getAdvId());
                if(result==null){
                    return "{'adverst':false}";
                }else{
                    boolean saving =  myService.remove(result);
                    if(saving){
                        return "{'adverst':success}";
                    }
                    return "{'adverst':false}";
                }
            }
        }
        return "{'adverst':false}";
    }








}




