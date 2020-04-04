package kg.bish.courier.service;

import kg.bish.courier.models_to_db.*;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Repository
@Transactional
public class MyServiceForAdmin {

    @Autowired
    @Qualifier(value = "sessionFactory")
    SessionFactory session;



/*=======================ALL COURIERS============================*/

    public List getAllCoureirs(int start,int end){
        Criteria criteria = session.getCurrentSession().createCriteria(CourierDB.class);
        criteria.setFirstResult(start);
        criteria.setMaxResults(end);
        List list  = criteria.list();
        if(list!=null&&list.size()!=0){
            return list;
        }
        return null;
    }
    public List getAllCoureirs_NEW(int start,int end){
        Criteria criteria = session.getCurrentSession().createCriteria(CourierDB.class);
        criteria.add(Restrictions.eq("statusStr","NEW"));
        criteria.setFirstResult(start);
        criteria.setMaxResults(end);
        List list  = criteria.list();
        if(list!=null&&list.size()!=0){
            return list;
        }
        return null;
    }

    public List getAllCoureirs_SUCCESS(int start,int end){
        Criteria criteria = session.getCurrentSession().createCriteria(CourierDB.class);
        criteria.add(Restrictions.eq("statusStr","COURIER"));
        criteria.setFirstResult(start);
        criteria.setMaxResults(end);
        List list  = criteria.list();
        if(list!=null&&list.size()!=0){
            return list;
        }
        return null;
    }
/*=======================ALL COURIERS============================*/


/*=======================UPDATE============================*/
    public boolean update(CourierDB courierDB){
        String updateSecretKeySql = "update CourierDB set statusStr='COURIER' where id="+courierDB.getId();
        SQLQuery sqlQuery = session.getCurrentSession().createSQLQuery(updateSecretKeySql);
        sqlQuery.executeUpdate();
        return true;
    }
/*=======================UPDATE===========================*/



/*=======================REMOVE============================*/
public boolean remove(CourierDB courierDB){
    String updateSecretKeySql = "delete from CourierDB where id="+courierDB.getId();
    SQLQuery sqlQuery = session.getCurrentSession().createSQLQuery(updateSecretKeySql);
    sqlQuery.executeUpdate();
    return true;
}
/*=======================REMOVE===========================*/




/*=======================SIGN IN============================*/

/*=======================SIGN IN============================*/




/*=======================LAST ID COURIER============================*/

/*=======================LAST ID COURIER===========================*/




/*=======================SECRET KEY============================*/



/*=======================SECRET KEY===========================*/


/*=======================LOGIN============================*/

/*=======================LOGIN===========================*/


/*=======================ADVERST============================*/

/*=======================ADVERST===========================*/







/*=======================ADVERST_ORDER============================*/

/*=======================ADVERST_ORDER===========================*/






















































}

































