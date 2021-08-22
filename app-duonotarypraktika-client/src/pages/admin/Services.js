import React, {Component} from 'react';
import PropTypes from 'prop-types';
import AdminLayout from "../../component/AdminLayout";

class Services extends Component {
    render() {
        return (
            <div>
                <AdminLayout pathname={this.props.location.pathname}>
                    <h1>Rahmatillo page</h1>
                </AdminLayout>
            </div>
        );
    }
}

Services.propTypes = {};

export default Services;