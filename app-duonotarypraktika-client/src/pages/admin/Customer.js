import React, {Component} from 'react';
import PropTypes from 'prop-types';
import AdminLayout from "../../component/AdminLayout";

class Customer extends Component {
    render() {
        return (
            <div>
                <AdminLayout pathname={this.props.location.pathname}>
                    <h1>unKnown owner</h1>
                </AdminLayout>
            </div>
        );
    }
}

Customer.propTypes = {};

export default Customer;