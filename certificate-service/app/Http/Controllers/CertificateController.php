<?php

namespace App\Http\Controllers;

use App\Models\Certificate;
use Illuminate\Support\Str;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;
use Illuminate\Support\Facades\DB;

class CertificateController extends Controller
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

    public function generateCertificate(Request $request) {
        $validator = Validator::make($request->all(), array(
            'user_email' => 'required|exists:users,email',
            'event_id'  =>  'required|exists:events,id',
        ));

        if ($validator->fails()) {
            return $this->sendJsonErrorResponse($validator->errors()->first(),400);
        }
            $string = Str::random(10);
            $certificate = DB::table('event_registrations')
                                ->where([
                                    ['activated', '=', '1'],
                                    ['user_email', '=', $request->user_email],
                                    ['event_id', '=', $request->event_id],
                                    ['checkin', '=', '1'],
                                ])
                                ->update(['certificate' => $string]);

            if ($certificate == 1){
                return $this->sendFormattedJsonResponse($string, "Certificate generated", 201);
            }
            else {
                return $this->sendJsonErrorResponse("Sorry, there was an error during generate certificate. User user is not registered for the event or hasn't checked in",400);
            }

    }

    public function validateCertificate($certificate) {
        $certificate = DB::table('event_registrations')->where('certificate', $certificate)->first();
        if ($certificate){
            return $this->sendFormattedJsonResponse($certificate);
        } else {
            return $this->sendJsonErrorResponse("Certificate doesn't exists", 400);
        }

    }

}
