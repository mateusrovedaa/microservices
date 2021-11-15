<?php

namespace App\Http\Controllers;

use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;
use Illuminate\Support\Facades\DB;

class UserController extends Controller
{
    /**
     * Create a new controller instance.
     *
     * @return void
     */
    public function __construct()
    {
        //
    }

    public function getAll() {
        $users = User::all();
        return $this->sendFormattedJsonResponse($users);
    }

    public function getSingle(Request $request) {
        $validator = Validator::make($request->all(), array(
            'email' => 'required|exists:users,email',
        ));

        if ($validator->fails()) {
            return $this->sendJsonErrorResponse($validator->errors()->first(),400);
        }

        $user = User::where([
            ['email', '=', $request->email],
        ])->get();

        return $this->sendFormattedJsonResponse($user);
    }

}
