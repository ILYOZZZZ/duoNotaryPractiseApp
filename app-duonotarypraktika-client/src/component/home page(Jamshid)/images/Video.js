import React, {Component} from 'react';
import video from "./test.mp4"

class Video extends Component {
    render() {
        return (
            <div>
                <video width="710" height="412" controls>
                    <source src={video} type="video/mp4" />
                </video>
            </div>
        );
    }
}

export default Video;