import React, {Component} from 'react';
import AdminLayout from "../../component/AdminLayout";

class AdminOrders extends Component {
    render() {
        return (
            <div>
                <AdminLayout pathname={this.props.location.pathname}>
                    <h1>Sardor's Team Page</h1>
                </AdminLayout>
            </div>
        );
    }
}

export default AdminOrders;