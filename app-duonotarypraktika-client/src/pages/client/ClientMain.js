import React, {Component} from 'react';
import PropTypes from 'prop-types';
import ClientLayout from "../../component/ClientLayout";

class ClientMain extends Component {
    render() {
        return (
            <div>
                <ClientLayout pathname={this.props.location.pathname}>
                    <h1>clientning upComing,inProggress,complated ustun bop turadi</h1>
                </ClientLayout>
            </div>
        );
    }
}

ClientMain.propTypes = {};

export default ClientMain;