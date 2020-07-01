import {Configuration} from "./generated";

export const config : Configuration = new Configuration({accessToken: () => localStorage.getItem('authorization') || ''});
