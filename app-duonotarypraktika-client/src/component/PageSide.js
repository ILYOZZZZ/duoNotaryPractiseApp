import React from 'react'
import AdminMain from "../pages/admin/maın/AdminMain";
import AgentMain from "../pages/agent/AgentMain";
import Container from "reactstrap/lib/Container";

const PageSide = () => {
    const pathName = window.location.pathname
    // console.log(this.props,"dddddddddddd")
    return (
            <div className="page-side">
                {/*{this.props.children}*/}
            </div>
    )

}

export default PageSide