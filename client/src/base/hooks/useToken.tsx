import React, {useEffect, useState} from 'react';
import jwt_decode from 'jwt-decode';

interface JwtClaims {
    sub: string;
    tsk2_user_name: string;
    iss: string;
    exp: number;
    iat: number;
}

type ContextProps = {
    jwtTokenClaims: JwtClaims | undefined,
    setToken: (s: string) => void
}

const UserTokenContext = React.createContext<ContextProps>(
    {} as ContextProps
);

export const UserTokenContextProvider: React.FC = (props) => {
    const [jwtTokenClaims, setJwtTokenClaims] = useState<JwtClaims>();

    useEffect(() => {
        const savedToken = localStorage.getItem('authorization');
        if (savedToken) {
            const decodedToken = jwt_decode<JwtClaims>(savedToken);
            console.log(decodedToken);
            setJwtTokenClaims(decodedToken);
        }
    },[setJwtTokenClaims])

    const setToken = (tokenString: string) => {
        console.log("set jwtTokenClaims string called")
        if (tokenString) {
            localStorage.setItem('authorization', tokenString);
            let jwtClaims = jwt_decode<JwtClaims>(tokenString);
            console.log(jwtClaims);
            setJwtTokenClaims(jwtClaims);
        }
    }

    return (
        <UserTokenContext.Provider value={{jwtTokenClaims, setToken}}>
            {props.children}
        </UserTokenContext.Provider>
    );
}

export const useToken = () => React.useContext(UserTokenContext);
