import React from 'react';
import {BrowserRouter as Router, Route, RouteComponentProps} from 'react-router-dom';
import './App.css';
import {ClientOverviewCard} from './ClientOverviewCard';
import {DefaultClientService} from './DefaultClientService';
import {ClientDetail} from './ClientDetail';
import {DefaultAccountService} from './DefaultAccountService';

export interface ClientDetailMatchParams {
  clientId: string;
}

const App: React.FC = () => {
  const clientService = new DefaultClientService();
  const accountService = new DefaultAccountService();

  const clientOverview = () => <ClientOverviewCard clientService={clientService}/>
  const clientDetail = ({match}: RouteComponentProps<ClientDetailMatchParams>) =>
    <ClientDetail clientId={match.params.clientId} clientService={clientService} accountService={accountService}/>;

  return (
    <div className="App">
      <h1>Kotlin Demo</h1>
      <Router>
        <Route exact={true} path="/" component={clientOverview}/>
        <Route exact={true} path="/clients" component={clientOverview}/>
        <Route path="/clients/:clientId" component={clientDetail}/>
      </Router>
    </div>
  );
};

export default App;
