<?php

namespace App\Http\Controllers;

use App\Models\EventRegistration;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;

class EventRegistrationController extends Controller
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
        $registration = EventRegistration::where('activated', 1)->get();
        return $this->sendFormattedJsonResponse($registration);
    }

    public function cancelRegistration(Request $request) {
        $validator = Validator::make($request->all(), array(
            'id' => 'required|integer'
        ));

        if ($validator->fails()) {
            return $this->sendJsonErrorResponse($validator->errors()->first(),400);
        }

        try {
            $registration = EventRegistration::find($request->id);
            $registration->activated = 0;

            $registration->save();

            return $this->sendFormattedJsonResponse($registration, "Registration canceled successfully", 201);

        } catch (\Throwable $th) {
            return $this->sendJsonErrorResponse("Sorry, there was an error during registration, please try again", $th);
        }
    }

    public function registration(Request $request) {
        $validator = Validator::make($request->all(), array(
            'user_id' => 'required|exists:users,id',
            'event_id'  =>  'required|exists:events,id',
            'checkin' => 'required|boolean'
        ));

        if ($validator->fails()) {
            return $this->sendJsonErrorResponse($validator->errors()->first(),400);
        }

        try {

            $registration = new EventRegistration;
            $registration->user_id = $request->user_id;
            $registration->event_id = $request->event_id;
            $registration->activated = 1;
            $registration->email_sent = 0;
            $registration->checkin = $request->checkin;

            $registration->save();

            return $this->sendFormattedJsonResponse($registration, "Registration successfully", 201);

        } catch (\Throwable $th) {
            return $this->sendJsonErrorResponse("Sorry, there was an error during registration, please try again", $th);
        }
    }

}
