import React, {Component} from 'react';
import PropTypes from 'prop-types';
import AdminLayout from "../../component/AdminLayout";

class Settings extends Component {
    render() {
        return (
            <div>
                <AdminLayout pathname={this.props.location.pathname}>
                    <h1> There is settings component</h1>
                </AdminLayout>
            </div>
        );
    }
}

Settings.propTypes = {};

export default Settings;