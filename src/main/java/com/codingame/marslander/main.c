#include <stdlib.h>
#include <stdio.h>
#include <string.h>

typedef struct _Vector {
    double x, y
} Vector;

Vector GRAVITY;

typedef struct _Params {
    Vector p, v;
    int fuel, rotate, power;
} Params;

static int *findLandingZones(const Vector *surfaces, int nSurface, int *nZones);

int main()
{
    GRAVITY.x = 0;
    GRAVITY.y = -3.711;

    int nSurface;
    scanf("%d", &nSurface);
    Vector *surface = (Vector *)malloc(nSurface * sizeof(Vector));
    int landX, landY;
    for (int i = 0; i < nSurface; i++) {
        scanf("%d%d", &landX, &landY);
        surface[i].x = (double) landX;
        surface[i].y = (double) landY;
    }
    
    int nLandZone = 0;
    int *zones = findLandingZones(surface, nSurface, &nLandZone);

    Params params;
    while (1) {
        int X;
        int Y;
        int hSpeed; // the horizontal speed (in m/s), can be negative.
        int vSpeed; // the vertical speed (in m/s), can be negative.
        int fuel; // the quantity of remaining fuel in liters.
        int rotate; // the rotation angle in degrees (-90 to 90).
        int power; // the thrust power (0 to 4).
        scanf("%d%d%d%d%d%d%d", &X, &Y, &hSpeed, &vSpeed, &fuel, &rotate, &power);
        params.p.x = (double) X;
        params.p.y = (double) Y;
        params.v.x = (double) hSpeed;
        params.v.y = (double) vSpeed;
        params.fuel = fuel;
        params.rotate = rotate;
        params.power = power;

        // Write an action using printf(). DON'T FORGET THE TRAILING \n
        // To debug: fprintf(stderr, "Debug messages...\n");


        // 2 integers: rotate power. rotate is the desired rotation angle (should be 0 for level 1), power is the desired thrust power (0 to 4).
        printf("0 3\n");
    }

    return 0;
}

int *findLandingZones(const Vector *surfaces, int nSurface, int *nZones) {
    int *zones = 0;
    for(int i = 1; i < nSurface; ++i) {
        if(surfaces[i].y == surfaces
        [i - 1].y) {
            zones = (int *)realloc(zones, *nZones * sizeof(int));
            if(zones != 0) {
                zones[*nZones++] = i - 1;
            }
        }
    }
    return zones;
}
