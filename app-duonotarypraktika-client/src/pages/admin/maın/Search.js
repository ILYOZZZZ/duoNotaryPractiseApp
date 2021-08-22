import React, {useState,useEffect} from 'react';
import '../../../styles/search-style.scss'
import {useDispatch,useSelector} from "react-redux";

const  Search  =()=> {
        return (
            <div>
                <div className="input-group">
                    {/*<label htmlFor="search" className="labelSearch"><h6 className="text-black-50">Search</h6></label>*/}
                    <input type={'text'} name={'search'}  className={'searchInput input-field'} id={"search"}/>
                </div>
            </div>
        );

};

export default Search;