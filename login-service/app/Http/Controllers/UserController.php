<?php

namespace App\Http\Controllers;

use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Log;

class UserController extends Controller
{
    /**
     * Create a new controller instance.
     *
     * @return void
     */
    public function __construct()
    {
        return $this->middleware('auth');
    }

    /**
     * Get all users
     *
     * @authenticated
     * @queryParam q A search query field
     * @queryParam perPage Number of results to be returned per page. Defaults to 20
     * @queryParam page The page to be returned. Defaults to 1
     * @response {
     *  "success": true,
     *  "status": 200,
     *  "message": null,
     *  "data": {
     *     "current_page": 1,
     *   "data": [
     *       {
     *           "id": 2,
     *           "name": "Adeosun Courtney",
     *           "email": "courtney@court.ng",
     *           "created_at": "2020-09-21T19:24:55.000000Z",
     *           "updated_at": "2020-09-21T19:24:55.000000Z"
     *       },
     *       {
     *           "id": 1,
     *           "name": "Jesutomiwa Salam",
     *           "email": "jsalam886@me.ng",
     *           "created_at": "2020-09-21T18:49:02.000000Z",
     *           "updated_at": "2020-09-21T18:49:02.000000Z"
     *       },
     *       {
     *           "id": 4,
     *           "name": "Jesutomiwa Salam",
     *           "email": "tolak@gmail.com",
     *           "created_at": "2020-09-22T06:53:20.000000Z",
     *           "updated_at": "2020-09-22T06:53:20.000000Z"
     *       },
     *       {
     *           "id": 3,
     *           "name": "Mr Kola",
     *           "email": "tola@yahoo.com",
     *           "created_at": "2020-09-21T19:36:41.000000Z",
     *           "updated_at": "2020-09-21T19:36:41.000000Z"
     *       }
     *   ],
     *   "first_page_url": "http://localhost:8000/api/v1/users?page=1",
     *   "from": 1,
     *   "next_page_url": null,
     *   "path": "http://localhost:8000/api/v1/users",
     *   "per_page": 20,
     *   "prev_page_url": null,
     *   "to": 4
     *           }
     *   }
     * }
     */
    public function getAll(Request $request) {

        $perPage = $request->perPage ? $request->perPage : 20;

        if ($request->q) {
            $users = User::where('name', 'like', '%' . $request->q . '%')->orWhere('email', 'like', '%' . $request->q . '%')->orderBy('name')->simplePaginate($perPage);
        }

        else {
            $users = User::orderBy('name')->simplePaginate($perPage);
        }

        return $this->sendFormattedJsonResponse($users);
    }



    /**
     * Get a single user instance
     *
     * @authenticated
     * @response {
     *  "success": true,
     *  "status": 200,
     *  "message": null,
     *  "data": {
     *     "name": "Jesutomiwa Salam",
     *     "email": "tolak@gmail.com",
     *     "updated_at": "2020-09-22T06:53:20.000000Z",
     *     "created_at": "2020-09-22T06:53:20.000000Z",
     *     "id": 4
     *   }
     * }
     */
    public function getSingle($id) {
        $user = User::findOrFail($id);

        return $this->sendFormattedJsonResponse($user);
    }


    /**
     * Get profile of current logged in user
     *
     * @authenticated
     * @response {
     *  "success": true,
     *  "status": 200,
     *  "message": null,
     *  "data": {
     *     "name": "Jesutomiwa Salam",
     *     "email": "tolak@gmail.com",
     *     "updated_at": "2020-09-22T06:53:20.000000Z",
     *     "created_at": "2020-09-22T06:53:20.000000Z",
     *     "id": 4
     *   }
     * }
     */
    public function getUserProfile() {
        $user = User::findOrFail(Auth::id());

        return $this->sendFormattedJsonResponse($user);
    }


}
