import React from 'react'
import MenuSide from "../component/MenuSide";
import PageSide from "../component/PageSide";
import AdminMain from "./admin/maın/AdminMain";

const Cabinet =()=>{
const pathName=window.location.pathname;
        return (
            <div className="cabinet">
               <MenuSide/>
                <PageSide>
                    {pathName==='/admin/adminMain'?
                    <AdminMain/>:''}
                </PageSide>
            </div>
        )

}

Cabinet.protoTypes={}

export default Cabinet
