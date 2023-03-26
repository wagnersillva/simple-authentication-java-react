import {
    createBrowserRouter,
    RouterProvider,
} from "react-router-dom";
import {mappingRoutes} from "./mapping";
import PrivateRoute from "../components/PrivateRoute";

export default function Routes(){
    const setPrivateRoute = route => ({...route, element: <PrivateRoute children={route.element} /> })

    const listRoutes = mappingRoutes.map( route => {
        const { isPrivate, ...rest } = route;
        return isPrivate ? setPrivateRoute(rest) : rest;
    })

    return <RouterProvider router={createBrowserRouter(listRoutes)} />
}