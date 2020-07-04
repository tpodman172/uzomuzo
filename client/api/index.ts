import * as Generated from "./generated";
import {config} from "./config";

const TasksApi = new Generated.TasksApi(config);
const LoginApi = new Generated.LoginApi(config);

export {TasksApi, LoginApi};
