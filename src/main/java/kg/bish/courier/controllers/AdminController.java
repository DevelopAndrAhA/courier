package kg.bish.courier.controllers;

import kg.bish.courier.Config;
import kg.bish.courier.models_to_db.AdminEntity;
import kg.bish.courier.models_to_db.CourierDB;
import kg.bish.courier.models_to_serialize.AdminJSON;
import kg.bish.courier.models_to_serialize.CourierJSON;
import kg.bish.courier.service.MyService;
import kg.bish.courier.service.MyServiceForAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    MyService myService;
    @Autowired
    MyServiceForAdmin service;

    Config config = new Config();




    @ResponseBody
    @RequestMapping(value = "signIn",method = RequestMethod.POST)
    public Object signIn(@RequestBody AdminJSON adminJSON) {
        try{
            if(adminJSON.getLogin()!=null&&!adminJSON.getLogin().isEmpty()){
                if(adminJSON.getPassword()!=null&&!adminJSON.getPassword().isEmpty()){
                    AdminEntity adminEntity = new AdminEntity();
                    adminEntity.setLogin(adminJSON.getLogin());
                    adminEntity.setPassword(adminJSON.getPassword());
                    adminEntity = myService.signIn(adminEntity);
                    AdminJSON adminJSON1 = new AdminJSON();
                    adminJSON1.setId(adminEntity.getId());
                    adminJSON1.setLogin(adminEntity.getLogin());
                    adminJSON1.setPassword(adminEntity.getPassword());
                    return adminJSON1;
                }
            }

        }catch (Exception e){e.printStackTrace();}
        return "{'result':'error'}";
    }


    @ResponseBody
    @RequestMapping(value = "all_couriers",method = RequestMethod.POST)
    public Object getAllCouriers(@RequestParam String start,@RequestParam String end) {
        try{
            List<CourierDB> list = service.getAllCoureirs(Integer.valueOf(start),Integer.valueOf(end));
            List couriersJSON = new ArrayList<>();
            if(list!=null&&list.size()!=0){
                for(int i=0;i<list.size();i++){
                    CourierDB courierDB = list.get(i);
                    CourierJSON courierJSON = new CourierJSON();

                    courierJSON.setPhone(courierDB.getPhone());
                    courierJSON.setId(courierDB.getId());
                    courierJSON.setPassword(courierDB.getPassword());
                    courierJSON.setLogin(courierDB.getLogin());
                    courierJSON.setPhoto_p1(courierDB.getPhoto_p1());
                    courierJSON.setFIO(courierDB.getFIO());
                    courierJSON.setPhoto_p2(courierDB.getPhoto_p2());
                    courierJSON.setStatusStr(courierDB.getStatusStr());

                    couriersJSON.add(courierJSON);
                }
                return couriersJSON;
            }
            return "{result:null}";
        }catch (Exception e){e.printStackTrace();}
        return "{'result':'error'}";
    }
    @ResponseBody
    @RequestMapping(value = "all_new_couriers",method = RequestMethod.POST)
    public Object getAllNewCouriers(@RequestParam String start,@RequestParam String end) {
        try{
            List<CourierDB> list = service.getAllCoureirs_NEW(Integer.valueOf(start),Integer.valueOf(end));
            List couriersJSON = new ArrayList<>();
            if(list!=null&&list.size()!=0){
                for(int i=0;i<list.size();i++){
                    CourierDB courierDB = list.get(i);
                    CourierJSON courierJSON = new CourierJSON();

                    courierJSON.setPhone(courierDB.getPhone());
                    courierJSON.setId(courierDB.getId());
                    courierJSON.setPassword(courierDB.getPassword());
                    courierJSON.setLogin(courierDB.getLogin());
                    courierJSON.setPhoto_p1(courierDB.getPhoto_p1());
                    courierJSON.setFIO(courierDB.getFIO());
                    courierJSON.setPhoto_p2(courierDB.getPhoto_p2());
                    courierJSON.setStatusStr(courierDB.getStatusStr());

                    couriersJSON.add(courierJSON);
                }
                return couriersJSON;
            }
            return "{result:null}";
        }catch (Exception e){e.printStackTrace();}
        return "{'result':'error'}";
    }
    @ResponseBody
    @RequestMapping(value = "all_success_couriers",method = RequestMethod.POST)
    public Object getAllSUCCESSCouriers(@RequestParam String start,@RequestParam String end) {
        try{
            List<CourierDB> list = service.getAllCoureirs_SUCCESS(Integer.valueOf(start), Integer.valueOf(end));
            List couriersJSON = new ArrayList<>();
            if(list!=null&&list.size()!=0){
                for(int i=0;i<list.size();i++){
                    CourierDB courierDB = list.get(i);
                    CourierJSON courierJSON = new CourierJSON();

                    courierJSON.setPhone(courierDB.getPhone());
                    courierJSON.setId(courierDB.getId());
                    courierJSON.setPassword(courierDB.getPassword());
                    courierJSON.setLogin(courierDB.getLogin());
                    courierJSON.setPhoto_p1(courierDB.getPhoto_p1());
                    courierJSON.setFIO(courierDB.getFIO());
                    courierJSON.setPhoto_p2(courierDB.getPhoto_p2());
                    courierJSON.setStatusStr(courierDB.getStatusStr());

                    couriersJSON.add(courierJSON);
                }
                return couriersJSON;
            }
            return "{result:null}";
        }catch (Exception e){e.printStackTrace();}
        return "{'result':'error'}";
    }



    @ResponseBody
    @RequestMapping(value = "check_courier",method = RequestMethod.POST)
    public Object checkCourier(@RequestBody CourierJSON courierJSON) {
        try{
            if(courierJSON.getId()!=0){
                CourierDB courierDB = new CourierDB();
                courierDB.setId(courierJSON.getId());
                boolean res = service.update(courierDB);
                if(res){
                    return "{'result':'success'}";
                }
            }
        }catch (Exception e){e.printStackTrace();}
        return "{'result':'error'}";
    }

    @ResponseBody
    @RequestMapping(value = "del_courier",method = RequestMethod.POST)
    public Object delCourier(@RequestBody CourierJSON courierJSON) {
        try{
            if(courierJSON.getId()!=0){
                CourierDB courierDB = new CourierDB();
                courierDB.setId(courierJSON.getId());
                boolean res = service.remove(courierDB);
                if(res){
                    return "{'result':'success'}";
                }
            }
        }catch (Exception e){e.printStackTrace();}
        return "{'result':'error'}";
    }







}
