import {Configuration} from "./generated";

export const makeConfiguration = () => {
    const configuration = new Configuration();
    const authorization = localStorage.getItem('authorization');
    console.log('-----authorization-------');
    console.log(authorization);
    if(authorization){
        configuration.accessToken = authorization;
    }
    return configuration;
}

