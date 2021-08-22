import React, {Component} from 'react';
import ClientLayout from "../../component/ClientLayout";

class ClientOrder extends Component {
    render() {
        return (
            <div>
                <ClientLayout pathname={this.props.location.pathname}>
                    <h1>Client own order</h1>
                </ClientLayout>
            </div>
        );
    }
}

export default ClientOrder;