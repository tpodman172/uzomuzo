import * as React from "react";
import {Redirect} from 'react-router-dom'
import jwt_decode from "jwt-decode";

const Auth: React.FC<{}> = ({children}) => {
    return <>{isAuthenticated() ? children : <Redirect to="/login"/>}</>;
}

interface JwtClaims {
    sub: string;
    tsk2_user_name: string;
    iss: string;
    exp: number;
    iat: number;
}

const isAuthenticated = () => {
    const token = localStorage.getItem('authorization');
    if (token) {
        const jwtDecode = jwt_decode<JwtClaims>(token);
        if (new Date(jwtDecode.exp * 1000) > new Date()) { // exp of Jwt token is defined by intDate
            return true;
        }
    }
    return false;
}

export default Auth;