import * as React from 'react';
import * as ReactDOM from 'react-dom';
import reset from 'styled-reset'
import styled, {createGlobalStyle} from "styled-components";
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import BoardPage from "./task/component/pages/BoardPage";
import LoginPage from "./login/component/pages/LoginPage";
import UserRegistrationPage from "./user/component/pages/UserRegistrationPage";
import Auth from "./base/component/Auth";
import {UserTokenContextProvider} from "./base/hooks/useToken";
import {BoardAdminPage} from "./admin/component/pages/BoardAdminPage";

class App extends React.Component {
    render() {
        return (
            <Router>
                <StyledDiv>
                    <GlobalStyle/>
                    <UserTokenContextProvider>
                        <Switch>
                            <Route path='/login' exact component={LoginPage}/>
                            <Route path='/signUp' exact component={UserRegistrationPage}/>
                            <Auth>
                                <Switch>
                                    <Route path='/board' exact component={BoardPage}/>
                                    <Route path='/boardAdmin' exact component={BoardAdminPage}/>
                                    <Route component={BoardPage}/>
                                </Switch>
                            </Auth>
                        </Switch>
                    </UserTokenContextProvider>
                </StyledDiv>
            </Router>
        );
    }
}

const GlobalStyle = createGlobalStyle`
  ${reset}
  h1 {
    font-size:20px;
  }
  h2 {
    font-size:16px;
  }
  /* other styles */
`
const StyledDiv = styled.div`
margin : 12px
`
ReactDOM.render(<App/>, document.querySelector('#app'));