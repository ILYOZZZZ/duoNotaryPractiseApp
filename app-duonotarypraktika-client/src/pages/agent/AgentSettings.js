import React, {Component} from 'react';
import AgentLayout from "../../component/AgentLayout";
import ClientLayout from "../../component/ClientLayout";

class AgentSettings extends Component {
    render() {
        return (
            <div>
                <AgentLayout pathname={this.props.location.pathname}>
                    <h1>Agent Settings</h1>
                </AgentLayout>
            </div>
        );
    }
}

export default AgentSettings;