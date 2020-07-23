import * as Generated from "./generated";
import {makeConfiguration} from "./config";

const TasksApi = () => new Generated.TasksApi(makeConfiguration());
const LoginApi = () => new Generated.AuthenticationApi(makeConfiguration());
const UserApi = () => new Generated.UserApi(makeConfiguration());

export {TasksApi, LoginApi, UserApi};
