import * as Generated from "./generated";
import {makeConfiguration} from "./config";

const TasksApi = () => new Generated.TasksApi(makeConfiguration());
const LoginApi = () => new Generated.AuthenticationApi();

export {TasksApi, LoginApi};
