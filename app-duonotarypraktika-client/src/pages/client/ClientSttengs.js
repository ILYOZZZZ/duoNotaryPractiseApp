import React, {Component} from 'react';
import ClientLayout from "../../component/ClientLayout";

class ClientSttengs extends Component {
    render() {
        return (
            <div>
                <ClientLayout pathname={this.props.location.pathname}>
                    <h1>Client setting</h1>
                </ClientLayout>
            </div>
        );
    }
}

export default ClientSttengs;