import React, {Component} from 'react';
import PropTypes from 'prop-types';
import AdminLayout from "../../component/AdminLayout";

class Blog extends Component {
    render() {
        return (
            <div>
                <AdminLayout pathname={this.props.location.pathname}>
                    <h1>Ilyos page</h1>
                </AdminLayout>
            </div>
        );
    }
}

Blog.propTypes = {};

export default Blog;