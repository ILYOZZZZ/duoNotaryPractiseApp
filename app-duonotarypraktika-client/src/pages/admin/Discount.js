import React, {Component} from 'react';
import PropTypes from 'prop-types';
import AdminLayout from "../../component/AdminLayout";

class Discount extends Component {
    render() {
        return (
            <div>
                <AdminLayout pathname={this.props.location.pathname}>
                    <h1>Sardor's team page maxsus</h1>
                </AdminLayout>
            </div>
        );
    }
}

Discount.propTypes = {};

export default Discount;