package kg.bish.courier.service;

import kg.bish.courier.Config;
import kg.bish.courier.models_to_db.*;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Repository
@Transactional
public class MyService {

    @Autowired
    @Qualifier(value = "sessionFactory")
    SessionFactory session;
    Config conf = new Config();
/*=======================SAVING============================*/
    public boolean save(ClientDB clientDB){
       session.getCurrentSession().save(clientDB);
        return  true;
    }
    public boolean save(CourierDB courierDB){
        session.getCurrentSession().save(courierDB);
        return  true;
    }
    public boolean save(AdvertisingDB advertisingDB){
        session.getCurrentSession().save(advertisingDB);
        return  true;
    }
    public boolean save(ADV_COURIER_TB adv_courier_tb){
        session.getCurrentSession().save(adv_courier_tb);
        return  true;
    }
    public boolean save(AdminEntity adminEntity){
        session.getCurrentSession().save(adminEntity);
        return  true;
    }
/*=======================SAVING============================*/



/*=======================UPDATE============================*/
    public boolean update(CourierDB courierDB){
        String updateSecretKeySql = "update CourierDB set secretKey='"+courierDB.getSecretKey()+"' where id="+courierDB.getId();
        SQLQuery sqlQuery = session.getCurrentSession().createSQLQuery(updateSecretKeySql);
        sqlQuery.executeUpdate();
        return true;
    }
    public boolean update(ClientDB clientDB){
        String updateSecretKeySql = "update ClientDB set secretKey='"+clientDB.getSecretKey()+"' where id="+clientDB.getId();
        SQLQuery sqlQuery = session.getCurrentSession().createSQLQuery(updateSecretKeySql);
        sqlQuery.executeUpdate();
        return true;
    }

    public boolean updateOut(CourierDB courierDB){
        String updateSecretKeySql = "update CourierDB set secretKey=0 where id="+courierDB.getId()+" and secretkey='"+courierDB.getSecretKey()+"'";
        SQLQuery sqlQuery = session.getCurrentSession().createSQLQuery(updateSecretKeySql);
        sqlQuery.executeUpdate();
        return true;
    }
    public boolean updateOut(ClientDB clientDB){
        String updateSecretKeySql = "update ClientDB set secretKey=0 where id="+clientDB.getId()+" and secretkey='"+clientDB.getSecretKey()+"'";
        SQLQuery sqlQuery = session.getCurrentSession().createSQLQuery(updateSecretKeySql);
        sqlQuery.executeUpdate();
        return true;
    }
/*=======================UPDATE============================*/
/*=======================REMOVE============================*/
    public boolean remove(ADV_COURIER_TB adv_courier_tb){
        session.getCurrentSession().remove(adv_courier_tb);
        return  true;
    }
/*=======================REMOVE===========================*/

/*=======================SIGN IN============================*/
    public CourierDB signIn(CourierDB courierDB){
       Criteria criteria = session.getCurrentSession().createCriteria(CourierDB.class);
        criteria.add(Restrictions.eq("login",courierDB.getLogin()));
        criteria.add(Restrictions.eq("password",courierDB.getPassword()));
        CourierDB courierDB1 = (CourierDB) criteria.uniqueResult();
        if(courierDB1!=null){
            return courierDB;
        }
        return null;
    }
    public ClientDB signIn(ClientDB clientDB){
        Criteria criteria = session.getCurrentSession().createCriteria(ClientDB.class);
        criteria.add(Restrictions.eq("login",clientDB.getLogin()));
        criteria.add(Restrictions.eq("password",clientDB.getPassword()));
        ClientDB clientDB1 = (ClientDB) criteria.uniqueResult();
        if(clientDB1!=null){
            return clientDB1;
        }
        return null;
    }
    public AdminEntity signIn(AdminEntity adminEntity){
        Criteria criteria = session.getCurrentSession().createCriteria(AdminEntity.class);
        criteria.add(Restrictions.eq("login",adminEntity.getLogin()));
        criteria.add(Restrictions.eq("password",adminEntity.getPassword()));
        AdminEntity adminEntity1 = (AdminEntity) criteria.uniqueResult();
        if(adminEntity1!=null){
            return adminEntity1;
        }
        return null;
    }
/*=======================SIGN IN============================*/




/*=======================LAST ID COURIER============================*/
    public long getLastContCourier(){
        SQLQuery sqlQuery = session.getCurrentSession().createSQLQuery("select * from CourierDB  order by id desc limit 1");
        List list = sqlQuery.list();
        CourierDB courierDB =(CourierDB) list.get(0);
        if(courierDB!=null){
            return courierDB.getId();
        }
        return 0;
    }
    public long getSelfId(String tableName,String secretKey,String login)  {
        long id = 0L;
        Criteria criteria= null;
        try {
            if(tableName.equals("courierdb")){
                criteria = session.getCurrentSession().createCriteria(CourierDB.class);
                criteria.add(Restrictions.eq("secretKey",secretKey));
                criteria.add(Restrictions.eq("login",login));
                CourierDB courierDB = (CourierDB)criteria.uniqueResult();
                id = courierDB.getId();
            }else if(tableName.equals("clientdb")){
                criteria = session.getCurrentSession().createCriteria(ClientDB.class);
                criteria.add(Restrictions.eq("secretKey",secretKey));
                criteria.add(Restrictions.eq("login",login));
                ClientDB clientDB = (ClientDB)criteria.uniqueResult();
                id = clientDB.getId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
/*=======================LAST ID COURIER===========================*/
/*=======================SECRET KEY============================*/
    public boolean verifySecretKey(CourierDB courierDB){
       Criteria criteria =  session.getCurrentSession().createCriteria(CourierDB.class);
        criteria.add(Restrictions.eq("secretKey",courierDB.getSecretKey()));
        criteria.add(Restrictions.eq("id",courierDB.getId()));
        CourierDB result = (CourierDB)criteria.uniqueResult();
        if(result!=null){
            return true;
        }
        return false;
    }
    public boolean verifySecretKey(ClientDB clientDB){
        Criteria criteria =  session.getCurrentSession().createCriteria(ClientDB.class);
        criteria.add(Restrictions.eq("secretKey",clientDB.getSecretKey()));
        criteria.add(Restrictions.eq("id",clientDB.getId()));
        ClientDB result = (ClientDB)criteria.uniqueResult();
        if(result!=null){
            return true;
        }
        return false;
    }
/*=======================SECRET KEY===========================*/
/*=======================LOGIN============================*/
    public boolean verifyLOGIN(CourierDB courierDB){
    Criteria criteria =  session.getCurrentSession().createCriteria(CourierDB.class);
    criteria.add(Restrictions.eq("login",courierDB.getLogin()));
    List list = (List)criteria.list();
    if(list!=null&&list.size()!=0){
        return true;
    }
    return false;
}
    public boolean verifyLOGIN(ClientDB clientDB){
        Criteria criteria =  session.getCurrentSession().createCriteria(ClientDB.class);
        criteria.add(Restrictions.eq("login",clientDB.getLogin()));
        List list = (List)criteria.list();
        if(list!=null&&list.size()!=0){
            return true;
        }
        return false;
    }
/*=======================LOGIN===========================*/


/*=======================ADVERST============================*/
    public List<AdverstOut> getAllAdverts(int startId,int endId){
        List<AdverstOut> advertisingDBs = new ArrayList<>();
        String sqlIds = "select adv_id from ADV_COURIER_TB t";
        SQLQuery query = session.getCurrentSession().createSQLQuery(sqlIds);
        List l = query.list();
        StringBuffer sql  = new StringBuffer("SELECT  ad.* FROM advertisingdb ad where ad.adverst_id not in(");
        for(int i=0;i<l.size();i++){
            sql.append(l.get(i)+",");
        }
        sql.append("0) ");
        sql.append("OFFSET  " + startId + " LIMIT " + endId + ";");



        SQLQuery sqlQuery = session.getCurrentSession().createSQLQuery(sql.toString()).addEntity(AdvertisingDB.class);
        List list =  sqlQuery.list();
        if(list!=null&&list.size()!=0){
            for(int i =0;i<list.size();i++){
                AdvertisingDB advertisingDB  = new AdvertisingDB();
                List<Product> products = new ArrayList<>();
                AdvertisingDB tmpAdverst = (AdvertisingDB)list.get(i);
                List <Product> tmpProducts  = tmpAdverst.getProductses();
                for(int k =0;k<tmpProducts.size();k++){
                    Product tmpProduct = tmpProducts.get(k);
                    Product product = new Product();
                        product.setId(tmpProduct.getId());
                        product.setName(tmpProduct.getName());
                        product.setPrice(tmpProduct.getPrice());
                        product.setWeight(tmpProduct.getWeight());
                        product.setCount(tmpProduct.getCount());
                    products.add(product);
                }

                advertisingDB.setId(tmpAdverst.getId());
                advertisingDB.setProductses(products);
                advertisingDB.setLatitude(tmpAdverst.getLatitude());
                advertisingDB.setLongitude(tmpAdverst.getLongitude());
                advertisingDB.setAll_price(tmpAdverst.getAll_price());
                advertisingDB.setProductses(products);
                AdverstOut adverstOut = new AdverstOut();
                    adverstOut.setAdvertisingDB(advertisingDB);
                Criteria criteria = session.getCurrentSession().createCriteria(ClientDB.class);
                criteria.add(Restrictions.eq("id", tmpAdverst.getClient_id()));
                ClientDB clientDB = (ClientDB)criteria.uniqueResult();
                        adverstOut.setClientDB(clientDB);
                advertisingDBs.add(adverstOut);
            }
            return advertisingDBs;
        }
        return null;
    }
    public AdvertisingDB getAdverts(long id){
    Criteria criteria = session.getCurrentSession().createCriteria(AdvertisingDB.class);
    criteria.add(Restrictions.eq("id",id));
    AdvertisingDB advertisingDB = (AdvertisingDB) criteria.uniqueResult();
    if(advertisingDB!=null){
        return advertisingDB;
    }
    return null;
}
/*=======================ADVERST===========================*/







/*=======================ADVERST_ORDER============================*/
    public ADV_COURIER_TB checkAdverstStatus(long adv_id){
        Criteria criteria = session.getCurrentSession().createCriteria(ADV_COURIER_TB.class);
        criteria.add(Restrictions.eq("adv_id",adv_id));
        ADV_COURIER_TB adv_courier_tb = (ADV_COURIER_TB) criteria.uniqueResult();
        if(adv_courier_tb!=null){
            return adv_courier_tb;
        }
        return null;
    }
/*=======================ADVERST_ORDER===========================*/






















































}

































