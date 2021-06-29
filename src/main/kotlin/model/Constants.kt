package model

import utils.iterop.three.Vector3

// INTERESTING - Need to use a singleton object top level val's were not initialised
//               when some other code (Geometry construction) was executed
object Constants {
    val GAME_WIDTH = 60.0
    val GAME_HEIGHT = 24.0
    val GAME_CENTRE = Position(GAME_WIDTH / 2, GAME_HEIGHT / 2)

    val CAMERA_POSITION = Vector3(GAME_CENTRE.x, GAME_CENTRE.y * 0.9, 22.0)
    val CAMERA_FOCUS_POSITION = GAME_CENTRE.toVector3()

    val BALL_RADIUS = 0.5
    val BALL_Z = 0.0
    val BAT_Z = BALL_Z
    val BAT_Y = 0.0
    val BAT_WIDTH = 8
    val BAT_HEIGHT = 1.0

    val BOTTOM_HEIGHT = BAT_HEIGHT + BALL_RADIUS

    val MAX_BALL_SPEED = 9.1
    val MAX_BAT_SPEED = 50.0

    val MAX_BEEP = 8

    val BRICK_WIDTH = 3.0
    val BRICK_HALF_WIDTH = BRICK_WIDTH / 2
    val BRICK_HEIGHT = 1.0
    val BRICK_HALF_HEIGHT = BRICK_HEIGHT / 2
}


