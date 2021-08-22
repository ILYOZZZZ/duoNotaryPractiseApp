import React, {useEffect, useState} from 'react';
// import axios from 'axios'



const GetDocFromServer = () => {

    return (
        <div>
            <button
                style={{marginTop:'10px'}}
                type='button'>
                <a
                    target='_blank'
                    href="http://localhost/api/docverify?download=false&orderId=all&docId=3003E613-67F4-4881-8E4E-0B13631D34B2"
                >Open document in new mirror</a>
            </button>

            <button >
                <a
                    href="http://localhost/api/docverify?download=true&orderId=all&docId=3003E613-67F4-4881-8E4E-0B13631D34B2"
                    download
                >Download document</a>

            </button>


        </div>
    );

}

export default GetDocFromServer;