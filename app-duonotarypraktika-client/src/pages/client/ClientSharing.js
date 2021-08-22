import React, {Component} from 'react';
import ClientLayout from "../../component/ClientLayout";

class ClientSharing extends Component {
    render() {
        return (
            <div>
                <ClientLayout pathname={this.props.location.pathname}>
                    <h1>Client sharing</h1>
                </ClientLayout>
            </div>
        );
    }
}

export default ClientSharing;