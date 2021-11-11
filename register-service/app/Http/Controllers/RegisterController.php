<?php

namespace App\Http\Controllers;

use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Facades\Validator;

class RegisterController extends Controller
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

    /**
     * Register as a new user
     *
     * @bodyParam name string required The name of the user
     * @bodyParam email string required The email of the user
     * @bodyParam password string required The password
     * @response {
     *  "success": true,
     *  "status": 201,
     *  "message": "User created successfully",
     *  "data": {
     *     "name": "Jesutomiwa Salam",
     *     "email": "tolak@gmail.com",
     *     "updated_at": "2020-09-22T06:53:20.000000Z",
     *     "created_at": "2020-09-22T06:53:20.000000Z",
     *     "id": 4
     *   }
     * }
     */
    public function register(Request $request) {
        $validator = Validator::make($request->all(), array(
            'name' => 'required|max:255',
            'email'  =>  'required|unique:users|email:rfc',
            'password' => 'required|unique:users'
        ));

        if ($validator->fails()) {
            return $this->sendJsonErrorResponse($validator->errors()->first(),400);
        }

        try {

            $user = new User;
            $user->name = $request->name;
            $user->email = $request->email;
            $user->password = Hash::make($request->password);

            $user->save();

            return $this->sendFormattedJsonResponse($user, "User created successfully", 201);

        } catch (\Throwable $th) {
            return $this->sendJsonErrorResponse("Sorry, there was an error during registration, please try again", 400);
        }
    }

}
