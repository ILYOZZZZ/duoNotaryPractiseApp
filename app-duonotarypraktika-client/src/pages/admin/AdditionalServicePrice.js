import React, {Component} from 'react';
import AdminLayout from "../../component/AdminLayout";

const AdditionalServicePrice = (props) => {

    return (
        <div>
            <AdminLayout pathname={props.location.pathname}>
                <h1>Abbos Additional Service Price</h1>
            </AdminLayout>
        </div>
    );

};

export default AdditionalServicePrice;