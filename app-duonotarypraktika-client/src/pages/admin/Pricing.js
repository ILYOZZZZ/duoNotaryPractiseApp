import React, {Component} from 'react';
import PropTypes from 'prop-types';
import AdminLayout from "../../component/AdminLayout";

class Pricing extends Component {
    render() {
        return (
            <div>
                <AdminLayout pathname={this.props.location.pathname}>
                    <h1>Abbos's Team page</h1>
                </AdminLayout>
            </div>
        );
    }
}

Pricing.propTypes = {};

export default Pricing;