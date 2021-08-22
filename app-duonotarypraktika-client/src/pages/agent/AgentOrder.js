import React, {Component} from 'react';
import AgentLayout from "../../component/AgentLayout";
import ClientLayout from "../../component/ClientLayout";

class AgentOrder extends Component {
    render() {
        return (
            <div>
                <AgentLayout pathname={this.props.location.pathname}>
                    <h1>Agent Order</h1>
                </AgentLayout>
            </div>
        );
    }
}

export default AgentOrder;