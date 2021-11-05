<?php

namespace App\Http\Controllers;

use App\Models\EventRegistration;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Date;
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

    public function getSingle($id) {
        $registration = EventRegistration::where([
            ['user_id', '=', $id],
            ['activated', '=', 1],
        ])->get();

        return $this->sendFormattedJsonResponse($registration);
    }


    public function getAll() {
        $registration = EventRegistration::where('activated', 1)->get();
        return $this->sendFormattedJsonResponse($registration);
    }

    public function cancelRegistration(Request $request) {
        $validator = Validator::make($request->all(), array(
            'user_id' => 'required|exists:users,id',
            'event_id'  =>  'required|exists:events,id',
            'date'  =>  'required|date',
        ));

        if ($validator->fails()) {
            return $this->sendJsonErrorResponse($validator->errors()->first(),400);
        }


        try {

            $registration = EventRegistration::where([
                ['user_id', '=', $request->user_id],
                ['event_id', '=', $request->event_id],
            ])->first();

            $requestDate = new \DateTime($request->date);
            $objectDate = new \DateTime($registration->event->event_date);

            if ($requestDate < $objectDate){

                $timePassed = $objectDate->diff($requestDate);

                if ($timePassed->days >= 2){

                    $registration->activated = 0;

                    $registration->save();

                    return $this->sendFormattedJsonResponse($registration, "Registration canceled successfully", 201);
                }
            }

            return $this->sendJsonErrorResponse("Sorry, it is only possible to cancel a registration up to 2 days before the event.");

        } catch (\Throwable $th) {
            return $this->sendJsonErrorResponse("Sorry, there was an error during canceling registration, please try again", $th);
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
