<?php

namespace App\Http\Controllers;

use App\Models\Checkin;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;
use Illuminate\Support\Facades\DB;

class CheckinController extends Controller
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

    public function checkin(Request $request) {
        $validator = Validator::make($request->all(), array(
            'user_email' => 'required|exists:users,email',
            'event_id'  =>  'required|exists:events,id',
        ));

        if ($validator->fails()) {
            return $this->sendJsonErrorResponse($validator->errors()->first(),400);
        }

            $checkin = DB::table('event_registrations')
                                ->where([
                                    ['activated', '=', '1'],
                                    ['user_email', '=', $request->user_email],
                                    ['event_id', '=', $request->event_id],
                                ])
                                ->update(['checkin' => 1]);

            if ($checkin == 1){
                return $this->sendFormattedJsonResponse($checkin, "Checkin successfully", 201);
            }
            else {
                return $this->sendJsonErrorResponse("Sorry, there was an error during checkin. User user is not registered for the event or has already checked in", 400);
            }

    }

}
