#include "Dandelion.h"

void Dandelion::takeAction()
{
    for (int i = 0; i < SPREAD_ATTEMPTS; i++)
    {
        if (rand() % 100 < 5)
            trySpread();
    }
}
Dandelion::Dandelion(World &wR, const int &xPos, const int &yPos)
    : Plant(wR, xPos, yPos, SYMBOL, STRENGTH, AllOrganisms::DANDELION) {this->typeS = "Mlecz";}
Dandelion::~Dandelion() {}