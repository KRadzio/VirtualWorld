#include "Nightshade.h"

Nightshade::Nightshade(World &wR, const int &xPos, const int &yPos)
    : Plant(wR, xPos, yPos, SYMBOL, STRENGTH, AllOrganisms::NIGHTSHADE) { this->typeS = "Wilecze Jagody"; }
Nightshade::~Nightshade() {}

bool Nightshade::specialAbility(Organism *other)
{
    other->die();
    this->die();
    string event = other->getTypeString() + " zjada wilcze jagody i umiera";
    this->worldRef.addEvent(event);
    return true;
}