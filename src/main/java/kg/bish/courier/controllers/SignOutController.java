package kg.bish.courier.controllers;

import kg.bish.courier.models_to_db.ClientDB;
import kg.bish.courier.models_to_db.CourierDB;
import kg.bish.courier.models_to_serialize.ClientJSON;
import kg.bish.courier.models_to_serialize.CourierJSON;
import kg.bish.courier.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

@Controller
@RequestMapping("sign_in")
public class SignOutController {

    @Autowired
    MyService myService;

    @ResponseBody
    @RequestMapping(value = "sign_in_courier",method = RequestMethod.POST)
    public Object signIn(@RequestBody CourierJSON courierJSON) {
        CourierDB courierDB = new CourierDB();
        courierDB.setLogin(courierJSON.getLogin());
        courierDB.setPassword(courierJSON.getPassword());
        courierDB = myService.signIn(courierDB);
        if(courierDB.getId()!=0){
            Random random = new Random(147);
            courierDB.setSecretKey(random.nextInt() + "");
            myService.update(courierDB);
            courierJSON.setSecretKey(courierDB.getSecretKey());
            courierJSON.setId(courierDB.getId());
            return courierJSON;
        }
        return "hello";
    }
    @ResponseBody
    @RequestMapping(value = "out_courier",method = RequestMethod.POST)
    public Object out(@RequestBody CourierJSON courierJSON) {
        CourierDB courierDB = new CourierDB();
        courierDB.setId(courierJSON.getId());
        courierDB.setSecretKey(courierJSON.getSecretKey());
        if(courierDB.getId()!=0&&!courierDB.getSecretKey().isEmpty()){
            myService.updateOut(courierDB);
            return courierJSON;
        }
        return "hello";
    }

    @ResponseBody
    @RequestMapping(value = "sign_in_client",method = RequestMethod.POST)
    public Object signIn(@RequestBody ClientJSON clientJSON) {
        ClientDB clientDB = new ClientDB();
        clientDB.setLogin(clientJSON.getLogin());
        clientDB.setPassword(clientJSON.getPassword());
        clientDB = myService.signIn(clientDB);
        if(clientDB.getId()!=0){
            Random random = new Random(147);
            clientDB.setSecretKey(random.nextInt() + "");
            myService.update(clientDB);
            clientJSON.setSecretKey(clientDB.getSecretKey());
            clientJSON.setId(clientDB.getId());
			clientJSON.setPhone(clientDB.getPhone());
            clientJSON.setPassword(clientDB.getPassword());
            clientJSON.setStatusStr(clientDB.getStatusStr());
            clientJSON.setAddress(clientDB.getAddress());
            clientJSON.setLatitude(clientDB.getLatitude());
            clientJSON.setLongitude(clientDB.getLongitude());
            return clientJSON;
        }
        return "hello";
    }


    @ResponseBody
    @RequestMapping(value = "out_client",method = RequestMethod.POST)
    public Object out(@RequestBody ClientJSON clientJSON) {
        ClientDB clientDB = new ClientDB();
        clientDB.setId(clientJSON.getId());
        clientDB.setSecretKey(clientJSON.getSecretKey());
        if(clientDB.getId()!=0&&!clientDB.getSecretKey().isEmpty()){
            myService.updateOut(clientDB);
            return clientJSON;
        }
        return "hello";
    }


}
