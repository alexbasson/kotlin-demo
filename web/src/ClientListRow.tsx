import {Client} from './Client';
import {default as React, ReactElement} from 'react';
import {Link} from "react-router-dom";

interface Props {
    client: Client;
}

export const ClientListRow = (props: Props): ReactElement => (
    <li>
        <Link to={`/clients/${props.client.id}`}>
            {props.client.firstName} {props.client.lastName}: {props.client.email}
        </Link>
    </li>
);
