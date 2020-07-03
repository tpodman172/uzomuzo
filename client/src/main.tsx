import * as React from 'react';
import * as ReactDOM from 'react-dom';
import reset from 'styled-reset'
import styled, {createGlobalStyle} from "styled-components";
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import BoardPage from "./task/Pages/BoardPage";
import LoginPage from "./login/pages/LoginPage";

class App extends React.Component {
    render() {
        return (
            <Router>
                <StyledDiv>
                    <GlobalStyle/>

                </StyledDiv>
                <Switch>
                    <Route path='/login' component={LoginPage}/>
                    <Route path='/' exact component={BoardPage}/>
                </Switch>

            </Router>
        );
    }
}

const GlobalStyle = createGlobalStyle`
  ${reset}
  h2 {
    font-size:18px;
  }
  /* other styles */
`
const StyledDiv = styled.div`
margin : 12px
`
ReactDOM.render(<App/>, document.querySelector('#app'));