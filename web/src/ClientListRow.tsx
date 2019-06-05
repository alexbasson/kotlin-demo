import {Client} from './Client';
import {default as React, ReactElement} from 'react';
import {Link} from "react-router-dom";

interface Props {
  client: Client;
}

export const ClientListRow = (props: Props): ReactElement => (
  <li className="link">
    <Link to={`/clients/${props.client.id}`}>
      <p>{props.client.firstName} {props.client.lastName}</p>
      <p>{props.client.email}</p>
    </Link>
  </li>
);
