import React from 'react'
import {useDispatch, useSelector} from "react-redux";
import {Link} from "react-router-dom";

const MenuSide =()=>{

    const dispatch=useDispatch()
    const userType = useSelector(state => state.auth.userType)
    const isOpen = useSelector(state => state.auth.isOpen)
    const pathName=window.location.pathname;
    console.log(userType,"TYPE")
        return (
            <div className="menu-side">
                <div style={{width: '130px', height: '60px', margin: 40}}>
                    <img src="/assets/duo_no%201logo.png" alt="logo"/>
                </div>
                {userType==='ROLE_SUPER_ADMIN'||userType==='ROLE_ADMIN'?
                 <div>
                     <Link to="/admin/adminMain"
                           className={
                               pathName === "/admin/adminMain" ?
                                   "active-tab" : "default-tab"
                           }>
                         <div className={
                             pathName === "/admin/adminMain" ?
                                 "active-tab m-2" : "default-tab m-2"
                         }>
                                                    <span style={{marginLeft: 25}}><img
                                                        src={pathName === "/admin/adminMain" ? "/assets/Vector.png" : "/assets/icons/Vector.svg"}/></span>

                             Main

                         </div>
                     </Link>
                 </div>
                    :userType==='ROLE_AGENT'?
                        <div>

                        </div>
                        :userType==='ROLE_USER'?
                            <div>

                            </div>
                            :''
                }
            </div>
        )

}
MenuSide.propTypes = {};

export default MenuSide