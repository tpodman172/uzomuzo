import * as React from 'react';
import * as ReactDOM from 'react-dom';
import reset from 'styled-reset'
import styled, {createGlobalStyle} from "styled-components";
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import BoardPage from "./task/Pages/BoardPage";
import LoginPage from "./login/pages/LoginPage";
import UserRegistrationPage from "./user/pages/UserRegistrationPage";

class App extends React.Component {
    render() {
        return (
            <Router>
                <StyledDiv>
                    <GlobalStyle/>
                    <Switch>
                        <Route path='/login' exact component={LoginPage}/>
                        <Route path='/signUp' exact component={UserRegistrationPage}/>
                        <Route path='/' exact component={BoardPage}/>
                    </Switch>
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